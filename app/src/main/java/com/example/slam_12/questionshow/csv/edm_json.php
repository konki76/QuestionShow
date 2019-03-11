<?php
class Manager
{
    private function dbConnect()
    {
        try{
            $db = new PDO('mysql:host=localhost;dbname=planchot;charset=utf8', 'root', '');
            return $db;
        }
        catch(Exception $e)
        {
            die('Erreur : '.$e->getMessage());
        }
    }
    public function getQuestion()
    {
        $db = $this->dbConnect();
        $qu = $db->query("SELECT codeQu, question, answer FROM question");
 
        while($donnees = $qu->fetch(PDO::FETCH_ASSOC))
            $output[] = $donnees;

        return print(json_encode($output));
    }
    public function getQuestionMatiere($matiere)
    {
        $db = $this->dbConnect();
        $qu = $db->prepare("SELECT codeQu, question, answer FROM question WHERE codeQu LIKE :matiere ");
        $matiere = $matiere."%";
        $qu->bindParam(':matiere', $matiere);
        $qu->execute();
 
        while($donnees = $qu->fetch(PDO::FETCH_ASSOC))
            $output[] = $donnees;

        return print(json_encode($output));
    }
}
if (isset($_GET['m']) && !empty($_GET['m'])) {
    $manager = new Manager();
    $manager->getQuestionMatiere($_GET['m']);
}
else {
    $manager = new Manager();
    $manager->getQuestion();
}