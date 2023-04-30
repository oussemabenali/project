<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Form\SearchReclamationType;
use App\Repository\ReclamationRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * @Route("/reclamation")
 */
class ReclamationController extends AbstractController
{
    /**
     * @Route("/pdf", name="pdf")
     */
    public  function displayPDF()
    {

        $options = new Options();
        $options->set('defaultFont', 'Courier');
        $reclamations = $this->getDoctrine()->getManager()->getRepository(Reclamation::class)->findAll();
        $html= $this->render('reclamation/pdf.html.twig', [
            'reclamations'=>$reclamations
        ]);
        $dompdf = new Dompdf($options);
        $dompdf->loadHtml($html);
        $dompdf->setPaper('A4', 'landscape');
        $dompdf->render();
        $dompdf->stream();

    }
    /**
     * @Route("/search/{searchString}", name="search")
     */
    public function search($searchString,SerializerInterface $serializerInterface,ReclamationRepository $reclamationRepository)
    {
        $rec = $reclamationRepository->findByExampleField($searchString);
        $data = $serializerInterface->normalize($rec,'json',['groups'=>'Reclamations']);

        return new JsonResponse($data);
    }
    /**
     * @Route("/", name="app_reclamation_index")
     */
    public function index(ReclamationRepository $reclamationRepository , Request $request): Response
    {

        $formsearchI = $this->createForm(SearchReclamationType::class);
        $formsearchI->handleRequest($request);
        if ($formsearchI->isSubmitted()) {
            $titre = $formsearchI->getData();
            $TSearch = $reclamationRepository->search($titre['sujet']);

            return $this->render("reclamation/index.html.twig",
                array("reclamations" => $TSearch,"formsearch" => $formsearchI->createView())) ;
        }
        return $this->render("reclamation/index.html.twig",array(
            "formsearch" => $formsearchI->createView(),
            'reclamations' => $reclamationRepository->findAll()));
    }

    /**
     * @Route("/new", name="app_reclamation_new", methods={"GET", "POST"})
     */
    public function new(Request $request, ReclamationRepository $reclamationRepository , MailerInterface $mailer): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamation->setDateRec(new \DateTime('@'.strtotime('now')));
            $email = (new Email())
                ->from('abptest22@gmail.com')
                ->to('arij.ouenniche@esprit.tn')

                ->subject('New reclamation')
                ->text('Reclamation sujet: '.$reclamation->getId().\PHP_EOL.
                    'Sujet:    '.$reclamation->getSujet().\PHP_EOL.
                    'Description:   '.$reclamation->getDescription().\PHP_EOL
                );

            $mailer->send($email);

            $reclamationRepository->add($reclamation);
            return $this->redirectToRoute('app_reclamation_new', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_reclamation_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation): Response
    {
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_reclamation_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamationRepository->add($reclamation);
            return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_reclamation_delete", methods={"POST"})
     */
    public function delete(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $reclamationRepository->remove($reclamation);
        }

        return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
    }


}
