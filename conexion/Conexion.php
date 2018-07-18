<?php
class Conexion {
    public static function getPDO(){
        $dbhost = "localhost";
        $dbuser = "concesionario";
        $dbpass = "concesionario";
        $dbname = "concesionario";
        $dns = "mysql:host={$dbhost};dbname={$dbname}";
        try{
          $pdo=new PDO($dns, $dbuser, $dbpass,array(PDO::ATTR_PERSISTENT=>true));
          $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
          return $pdo;
        }catch(PDOException $e) {
            echo $e->getMessage();
        }
    }
}
?>
