<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Repository\ReponseRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReponsefrController extends AbstractController
{
    /**
     * @Route("/reponsefr", name="app_reponsefr")
     */
    public function index(ReponseRepository $reponseRepository): Response
    {
        return $this->render('reponsefr/index.html.twig', [
            'reponses' => $reponseRepository->findAll(),
        ]);
    }


}
