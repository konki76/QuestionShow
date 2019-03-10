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
}
$manager = new Manager();
$manager->getQuestion();