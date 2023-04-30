<?php

namespace App\Controller;




use App\Entity\Programme;
use App\Entity\Reclamation;
use App\Repository\ProgrammeRepository;
use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;



/**
 * @Route("Reclamationjson")
 */

class ReclamationJsonController extends AbstractController
{
    /**
     * @Route("/getAllReclamation", name="tous_les_Reclamation")
     */
    public function getAllReclamation(ReclamationRepository $repo, NormalizerInterface $normalizer)
    {

        $rec = $this->getDoctrine()->getRepository(Reclamation::class)->findAll();
        $data = [];

        foreach ($rec as $reclamation) {
            $data[] = [
                'id' => $reclamation->getId(),
                'dateRec' => $reclamation->getDateRec(),
                'sujet' => $reclamation->getSujet(),
                'description' => $reclamation->getDescription(),

            ];
        }

        return new JsonResponse($data, Response::HTTP_OK);
    }



    /**
     * @Route("/newReclamation", name="cree_new_Reclamation")
     */
    public function addReclamation(NormalizerInterface $Normalizer,Request $request): JsonResponse
    {
        $data = json_decode($request->getContent(), true);


        $em = $this->getDoctrine()->getManager();
        $reclamation = new Reclamation();

        $reclamation->setSujet($request->get('sujet'));
        $reclamation->setDescription($request->get('description'));
        $em->persist($reclamation);
        $em->flush();

        $jsonContent= $Normalizer->normalize($reclamation,'json',['groups'=>"Reclamations"]);
        // return new Response(json_encode($jsonContent));;

        return new JsonResponse(['status' => 'Reclamation created!'], Response::HTTP_CREATED);
    }


    /**
     * @Route("/Reclamationjson/{id}", name="find_by_idReclamation")
     */
    public function ReclamationId($id, NormalizerInterface $normalizer, ReclamationRepository $repo)
    {
        $prog = $this->getDoctrine()->getRepository(Reclamation::class)->findByid($id);
        $data = [];

        foreach ($prog as $reclamation) {
            $data[] = [
                'id' => $reclamation->getId(),
                'dateRec' => $reclamation->getDateRec(),
                'sujet' => $reclamation->getSujet(),
                'description' => $reclamation->getDescription(),
            ];
        }

        return new JsonResponse($data);


    }

    /**
     * @Route("/deleteReclamation/{id}", name="delete_Reclamation_json")
     */
    public function deleteReclamation(Request $req, $id, NormalizerInterface $Normalizer)
    {

        $em = $this->getDoctrine()->getManager();
        $comm = $em->getRepository(Reclamation::class)->find($id);
        $em->remove($comm);
        $em->flush();
        $jsonContent = $Normalizer->normalize($comm, 'json', ['groups' => "Reclamations"]);

        return new Response("Reclamation deleted successfully" . json_encode($jsonContent));
    }


    /**
     * @Route("/updateReclamation/{id}", name="update_Reclamation_json")
     */
    public function updateReclamation($id,Request $request, NormalizerInterface $Normalizer): Response
    {
        $em = $this->getDoctrine()->getManager();
        $reclamation = $em->getRepository(Programme::class)->find($id);
        $reclamation->setSujet($request->get('sujet'));
        $reclamation->setDescription($request->get('description'));
        $em->flush();
        $jsonContent = $Normalizer->normalize($reclamation, 'json', ['groups' => 'Reclamations']);


        $data[] = [
            'id' => $reclamation->getId(),
            'dateRec' => $reclamation->getDateRec(),
            'sujet' => $reclamation->getSujet(),
            'description' => $reclamation->getDescription(),



        ];

        return new Response("Reclamation updated successfully" . json_encode($data));
    }


}