<?php
/**
 * Created by PhpStorm.
 * User: pacopulido
 * Date: 13/2/18
 * Time: 19:54
 */

class DB {
    private $connection;
    private static $_instance;
    private $dbhost = "localhost";
    private $dbuser = "concesionario";
    private $dbpass = "concesionario";
    private $dbname = "concesionario";
    /*
    Get an instance of the Database
    @return Instance
    */
    public static function getInstance(){
        if(!self::$_instance) {
            self::$_instance = new self();
        }
        return self::$_instance;
    }
    // Constructor
    private function __construct() {
        echo "hola";
        try{
            $dns = "mysql:host={$this->dbhost};dbname={$this->dbname}";
            $this->connection = new PDO($dns, $this->dbuser, $this->dbpass,array(PDO::ATTR_PERSISTENT=>true));
            $this->connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        }catch(PDOException $e){
            die("Failed to connect to DB: ". $e->getMessage());
        }
    }
    // Method clone is empty to prevent duplication of connection
    private function __clone(){}

    // Get the connection
    public function getConnection(){
        return $this->connection;
    }
}