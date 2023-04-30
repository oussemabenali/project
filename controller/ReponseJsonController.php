<?php

namespace App\Controller;





use App\Repository\RendezVousRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Reponse;
use App\Repository\ReponseRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/ReponseJson")
 */
class ReponseJsonController extends AbstractController
{
    /**
     * @Route("/getRps", name="tous_les_rps")
     */
    public function getReponses(ReponseRepository $repo, NormalizerInterface $normalizer)
    {

        $r = $this->getDoctrine()->getRepository(Reponse::class)->findAll();
        $data = [];

        foreach ($r as $rs) {
            $data[] = [
                'id' => $r->getId(),
                'reclamation_id' => $r->getReclamation(),
                'reponse' => $r->getReponse(),



            ];
        }

        return new JsonResponse($data, Response::HTTP_OK);
    }



    /**
     * @Route("/newrp", name="cree_new_rp")
     */
    public function addRp(NormalizerInterface $Normalizer,Request $request): JsonResponse
    {
        $data = json_decode($request->getContent(), true);


        $em = $this->getDoctrine()->getManager();
        $rdv = new Reponse();
        $rdv->setReclamation($request->get('reclamation_id'));
        $rdv->setReponse($request->get('reponse'));


        $em->persist($rdv);
        $em->flush();

        $jsonContent= $Normalizer->normalize($rdv,'json',['groups'=>"reponses"]);
        // return new Response(json_encode($jsonContent));;

        return new JsonResponse(['status' => 'reponse created!'], Response::HTTP_CREATED);
    }


    /**
     * @Route("/rps/{id}", name="find_by_idrdv")
     */
    public function rdvID($id, NormalizerInterface $normalizer, RendezVousRepository $repo)
    {
        $rdv = $this->getDoctrine()->getRepository(Reponse::class)->findById($id);
        $data = [];

        foreach ($rdv as $rdvz) {
            $data[] = [
                'id' => $rdvz->getId(),
                'reclamation_id' => $rdvz->getReclamation(),
                'reponse' => $rdvz->getReponse(),

            ];}

        return new JsonResponse($data);


    }

    /**
     * @Route("/deleterps/{id}", name="delete_rps_json")
     */
    public function deleteRDV(Request $req, $id, NormalizerInterface $Normalizer)
    {

        $em = $this->getDoctrine()->getManager();
        $rp = $em->getRepository(Reponse::class)->find($id);
        $em->remove($rp);
        $em->flush();
        $jsonContent = $Normalizer->normalize($rp, 'json', ['groups' => "reponses"]);

        return new Response("Rps deleted successfully" . json_encode($jsonContent));
    }


    /**
     * @Route("/updaterdv/{id}", name="update_rdv_json")
     */
    public function updateRDV($id,Request $request, NormalizerInterface $Normalizer): Response
    {
        $em = $this->getDoctrine()->getManager();
        $rdv = $em->getRepository(Reponse::class)->find($id);
        $rdv->setReclamation($request->get('reclamation_id'));
        $rdv->setReponse($request->get('reponse'));
        $em->flush();
        $jsonContent = $Normalizer->normalize($rdv, 'json', ['groups' => 'reponses']);


        $data[] = [
            'id' => $rdv->getId(),
            'reclamation_id' => $rdv->getReclamation(),
            'reponse' => $rdv->getReponse(),



        ];

        return new Response("rdv updated successfully" . json_encode($data));
    }


}
