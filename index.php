<?php
/**
 * Created by PhpStorm.
 * User: pacopulido
 * Date: 11/3/17
 * Time: 13:35
 */
require "vendor/autoload.php";
require "conexion/Conexion.php";

$app = new \Slim\App();

$conn = Conexion::getPDO();

//*************************************USUARIOS******************************************

$app = new \Slim\App(['settings' => ['displayErrorDetails' => true]]);

//metodo get para obtener todos los usuarios (FUNCIONA)

$app->get('/usuarios', function ($request, $response, $args) use ($conn){
    $ordenSql = "select * from usuarios";
    $statement = $conn->prepare($ordenSql);
    $statement->execute();
    $salida = $statement->fetchAll(PDO::FETCH_ASSOC);
    $statement = null;
    return $response->withStatus(200)
        ->withHeader('Content-Type', 'application/json')
        ->write(json_encode(["usuarios"=>$salida]));
});


//metodo get para obtener un usuario por su id (FUNCIONA)

$app->get('/usuarios/{id_user}', function ($request, $response, $args) use ($conn){
    $id_user = $args['id_user'];
    $ordenSql = "select * from usuarios where id_user=:id_user";
    $statement = $conn->prepare($ordenSql);
    $statement->bindParam(':id_user', $id_user, PDO::PARAM_INT);
    $statement->execute();
    $salida = $statement->fetch(PDO::FETCH_ASSOC);
    $statement = null;
    return $response->withStatus(200)
        ->withHeader('Content-Type', 'application/json')
        ->write(json_encode(["usuarios"=>$salida]));
});

//metodo post para insertar un usuario a la bd (FUNCIONA)

$app->post('/usuario', function ($request, $response, $args) use ($conn) {
    $password = $request->getParam('password');
    $nick = $request->getParam('nick');
    if (!isset($id_user) || !isset($nick)) {
        $body = $request->getBody();
        $jsonobj = json_decode($body);
        if ($jsonobj != null) {
            $password = $jsonobj->{'password'};
            $nick = $jsonobj->{'nick'};
        }
    }
    try {
        if (!isset($nick) || !isset($password)) {
            $salida = "No data";
        } else {
            $ordenSql = "insert into usuarios(nick,password) values(:nick,:password)";
            $statement = $conn->prepare($ordenSql); // creación de la sentencia .
            $statement->bindParam(':password', $password, PDO::PARAM_STR);
            $statement->bindParam(':nick', $nick, PDO::PARAM_STR);
            $conn->beginTransaction();
            $statement->execute();
            $conn->commit();
            $salida = "Ok";
        }
    }catch (PDOException $e) {
        return $response->withStatus(200)
            ->withHeader('Content-Type', 'application/json')
            ->write(json_encode(["msg"=>"Violada Primary key..."]));
    } finally {$statement = null;}
    return $response->withStatus(200)
        ->withHeader('Content-Type', 'application/json')
        ->write(json_encode(["msg"=>$salida]));
});

//metodo post para insertar una imagen en un usuario

//$app->post('/usuarios/{id_user}/imguser', function ($request, $response, $args)  use ($conn) {
//    $body = $request->getBody();
//    $jsonobj = json_decode($body);
//    $id_user=$args['id_user'];
//
//
//    //Si nos envian un fichero entrará aqui
//    if (!empty($_FILES)) {
//        $file = $_FILES["imguser"];
//        //$file = $_FILES;
//        $target_dir = "resources/imguser/";
//        $target_file = $target_dir . $file["name"];
//        $imageFileType = pathinfo($target_file, PATHINFO_EXTENSION);
//        $tmp_name=$file["tmp_name"];
//        $nombrefile = $id_user."_"."imguser"."_".time().".".$imageFileType;
//        $src = $target_dir.$nombrefile;
//        if(move_uploaded_file($tmp_name,$src)){
//            return $response->withStatus(200)
//                ->withHeader('Content-Type', 'application/json')
//                ->write(json_encode(["msg"=>"No se ha podido subir el fichero (fichero)"]));
//        }
//        //Si el archivo viene en cadena Base64 entrara aqui
//    }else if(isset($jsonobj->{'imguser'})){
//        $target_dir = "resources/imguser/";
//        $nombrefile = $id_user."_"."imguser"."_".time().".png";
//        $src= $target_dir.$nombrefile;
//        if(!file_put_contents($src,base64_decode($jsonobj->{'imguser'}))){
//            return $response->withStatus(200)
//                ->withHeader('Content-Type', 'application/json')
//                ->write(json_encode(["msg"=>"No se ha podido subir el fichero (base64)"]));
//
//        }
//    }
//    else {
//        return $response->withStatus(200)
//            ->withHeader('Content-Type', 'application/json')
//            ->write(json_encode(["msg"=>"No se ha detectado ningun File"]));
//    }
//    $ordenSql = "UPDATE usuarios set imguser=:file where id_user=:id_user";
//    try {
//
//        $statement = $conn->prepare($ordenSql);
//        $statement->bindParam(":file", $nombrefile, PDO::PARAM_STR);
//        $statement->bindParam(":id_user", $id_user, PDO::PARAM_STR);
//        $conn->beginTransaction();
//        $statement->execute();
//        $conn->commit();
//    } catch (Exception $e){
//        echo $e->getMessage();
//    }
//
//    return $response->withStatus(200)
//        ->withHeader('Content-Type', 'application/json')
//        ->write(json_encode(["msg"=>$nombrefile]));
//});


//*************************************COCHES********************************************************************

//metodo get para obtener todos los coches (FUNCIONA)

$app->get('/coches', function ($request, $response, $args) use ($conn){
    $ordenSql = "select * from coches";
    $statement = $conn->prepare($ordenSql);
    $statement->execute();
    $salida = $statement->fetchAll(PDO::FETCH_ASSOC);
    $statement = null;
    return $response->withStatus(200)
        ->withHeader('Content-Type', 'application/json')
        ->write(json_encode(["coches"=>$salida]));
});

//metodo get para obtener los coches por marca (FUNCIONA)

$app->get('/coches/{marca}', function ($request, $response, $args) use ($conn){
    $marca = $args['marca'];
    $ordenSql = "select * from coches where marca=:marca";
    $statement = $conn->prepare($ordenSql);
    $statement->bindParam(':marca', $marca, PDO::PARAM_INT);
    $statement->execute();
    $salida = $statement->fetchAll(PDO::FETCH_ASSOC);
    $statement = null;
    return $response->withStatus(200)
        ->withHeader('Content-Type', 'application/json')
        ->write(json_encode(["coches"=>$salida]));
});


//meotodo post para insertar un nuevo coche (FUNCIONA)

$app->post('/coche', function ($request, $response, $args) use ($conn) {
    $marca = $request->getParam('marca');
    $modelo = $request->getParam('modelo');
    $combustible = $request->getParam('combustible');
    $kilometros = $request->getParam('kilometros');
    $cambio = $request->getParam('cambio');
    $caballos = $request->getParam('caballos');
    $color = $request->getParam('color');
    $anioMatriculacion = $request->getParam('anioMatriculacion');
    $garantia = $request->getParam('garantia');
    $descripcion = $request->getParam('descripcion');
    $precio = $request->getParam('precio');
    $imgcar = $request->getParam('imgcar');
    $urlFabricante = $request->getParam('urlFabricante');
    if (!isset($marca)||!isset($modelo)||!isset($combustible)||!isset($kilometros)||!isset($cambio)||!isset($caballos)||!isset($color)||!isset($anioMatriculacion)||!isset($garantia)||!isset($descripcion)||!isset($precio)) {
        $body = $request->getBody();
        $jsonobj = json_decode($body);
        if ($jsonobj != null) {
            $marca = $jsonobj->{'marca'};
            $modelo = $jsonobj->{'modelo'};
            $combustible = $jsonobj->{'combustible'};
            $kilometros = $jsonobj->{'kilometros'};
            $cambio = $jsonobj->{'cambio'};
            $caballos = $jsonobj->{'caballos'};
            $color = $jsonobj->{'color'};
            $anioMatriculacion = $jsonobj->{'anioMatriculacion'};
            $garantia = $jsonobj->{'garantia'};
            $descripcion = $jsonobj->{'descripcion'};
            $precio = $jsonobj->{'precio'};
            $imgcar = $jsonobj->{'imgcar'};
            $urlFabricante = $jsonobj->{'urlFabricante'};
        }
    }
    if (!isset($marca)||!isset($modelo)||!isset($combustible)||!isset($kilometros)||!isset($cambio)||!isset($caballos)||!isset($color)||!isset($anioMatriculacion)||!isset($garantia)||!isset($descripcion)||!isset($precio)) {
        $salida = "No data";
    } else {
        $ordenSql = "insert into coches(marca, modelo, combustible, kilometros, cambio, caballos, color, anioMatriculacion, garantia, descripcion, precio, imgcar, urlFabricante) 
values(:marca, :modelo, :combustible, :kilometros, :cambio, :caballos, :color, :anioMatriculacion, :garantia, :descripcion, :precio, :imgcar, :urlFabricante)";
        $statement = $conn->prepare($ordenSql);
        $statement->bindParam(':marca', $marca, PDO::PARAM_STR);
        $statement->bindParam(':modelo', $modelo, PDO::PARAM_STR);
        $statement->bindParam(':combustible', $combustible, PDO::PARAM_STR);
        $statement->bindParam(':kilometros', $kilometros, PDO::PARAM_STR);
        $statement->bindParam(':cambio', $cambio, PDO::PARAM_STR);
        $statement->bindParam(':caballos', $caballos, PDO::PARAM_STR);
        $statement->bindParam(':color', $color, PDO::PARAM_STR);
        $statement->bindParam(':anioMatriculacion', $anioMatriculacion, PDO::PARAM_STR);
        $statement->bindParam(':garantia', $garantia, PDO::PARAM_STR);
        $statement->bindParam(':descripcion', $descripcion, PDO::PARAM_STR);
        $statement->bindParam(':precio', $precio, PDO::PARAM_STR);
        $statement->bindParam(':imgcar', $imgcar, PDO::PARAM_STR);
        $statement->bindParam(':urlFabricante', $urlFabricante, PDO::PARAM_STR);
        $conn->beginTransaction();
        $statement->execute();
        $idcoche=$conn->lastInsertId();
        $conn->commit();
        $coche= ["marca"=>$marca, "modelo"=>$modelo, "combustible"=>$combustible, "kilometros"=>$kilometros,
            "cambio"=>$cambio, "caballos"=>$caballos, "color"=>$color, "anioMatriculacion"=>$anioMatriculacion, "garantia"=>$garantia,
            "descripcion"=>$descripcion, "precio"=>$precio, "imgcar"=>$imgcar, "urlFabricante"=>$urlFabricante];
    }
    $statement = null;
    return $response->withStatus(200)
        ->withHeader('Content-Type', 'application/json')
        ->write(json_encode(["coches"=>$coche]));
});

//meotod post para insertar una imagen en un coche (FUNCIONA)

//$app->post('/coche/{id}/imgcar', function ($request, $response, $args)  use ($conn) {
//    $body = $request->getBody();
//    $jsonobj = json_decode($body);
//    $id=$args['id'];
//
//
//    //Si nos envian un fichero entrará aqui
//    if (!empty($_FILES)) {
//        $file = $_FILES["imgcar"];
//        //$file = $_FILES;
//        $target_dir = "resources/imgcar/";
//        $target_file = $target_dir . $file["name"];
//        $imageFileType = pathinfo($target_file, PATHINFO_EXTENSION);
//        $tmp_name=$file["tmp_name"];
//        $nombrefile = $id."_"."imgcar"."_".time().".".$imageFileType;
//        $src = $target_dir.$nombrefile;
//        if(move_uploaded_file($tmp_name,$src)){
//            return $response->withStatus(200)
//                ->withHeader('Content-Type', 'application/json')
//                ->write(json_encode(["msg"=>"No se ha podido subir el fichero"]));
//        }
//        //Si el archivo viene en cadena Base64 entrara aqui
//    }else if(isset($jsonobj->{'imgcar'})){
//        $target_dir = "resources/imgcar/";
//        $nombrefile = $id."_"."imgcar"."_".time().".png";
//        $src= $target_dir.$nombrefile;
//        if(!file_put_contents($src,base64_decode($jsonobj->{'imgcar'}))){
//            return $response->withStatus(200)
//                ->withHeader('Content-Type', 'application/json')
//                ->write(json_encode(["msg"=>"No se ha podido subir el fichero"]));
//
//        }
//    }
//    else {
//        return $response->withStatus(200)
//            ->withHeader('Content-Type', 'application/json')
//            ->write(json_encode(["msg"=>"No se ha detectado ningun File"]));
//    }
//    $ordenSql = "UPDATE coches set imgcar=:file where id=:id";
//    try {
//
//        $statement = $conn->prepare($ordenSql);
//        $statement->bindParam(":file", $nombrefile, PDO::PARAM_STR);
//        $statement->bindParam(":id", $id, PDO::PARAM_STR);
//        $conn->beginTransaction();
//        $statement->execute();
//        $conn->commit();
//    } catch (Exception $e){
//        echo $e->getMessage();
//    }
//
//    return $response->withStatus(200)
//        ->withHeader('Content-Type', 'application/json')
//        ->write(json_encode(["msg"=>$nombrefile]));
//});


$app->run();
$conn=null;

?>