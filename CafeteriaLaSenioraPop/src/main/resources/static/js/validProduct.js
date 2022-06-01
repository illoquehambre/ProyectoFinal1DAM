document.getElementById("nombre").addEventListener("blur", pasarNombreAMayusculas);
document.getElementById("precio").addEventListener("blur", comprobarPrecio);
document.getElementById("url").addEventListener("blur", comprobarUrl);

function revisarFormulario(){
	let resultado = false;

	//en las siguientes llamadas encadenadas con && hay que tener en cuenta que en el momento 
	//en el que una de las llamadas devuelva false, ya no se realizarán las siguientes
	resultado = pasarNombreAMayusculas() &&
				comprobarPrecio()&&
				comprobarUrl();
	

	formulario.enviar.className = resultado?"btn btn-success mb-2":"btn btn-danger mb-2";

	return resultado; //lo tengo a false para que nunca envíe el formulario, cuando esto entrara en producción, habría que poner return resultado;
}


function pasarNombreAMayusculas(){
	//Es más fácil acceder a los campos del formulario de esta forma, ya que existe un objeto
	// global que tiene por nombre el id que le hayamos dado al formulario. 
	//Además, puedo acceder a todos los campos a través del name o del id (indistintamente)
	// que le hayamos dado a cada campo
	let campoNombre = document.getElementById("nombre");

	let resultado = campoNombre.value!="";
	if(resultado){		
		let array =new Array();
		array=campoNombre.value.split('');
		let letra = array.shift();
		letra = letra.toUpperCase();
		array.unshift(letra);		
		campoNombre.value=array.join('');
		
			
		
	}
	
	//ver el comentario explicativo en el método cambiarApariencia
	cambiarApariencia(campoNombre,resultado);
	
	return resultado;
}

function comprobarPrecio(){
	let campoPrecio = document.getElementById("precio");

	let resultado =  campoPrecio.value>0;
	cambiarApariencia(campoPrecio,resultado);
	return resultado;
}

function comprobarUrl(){
	let campoUrl = document.getElementById("url");

	let resultado = /^(ftp|http|https):\/\/[^ "]+$/.test(campoUrl.value) || campoUrl.value=="";
	cambiarApariencia(campoUrl,resultado);
	return resultado;
	
	
}



function cambiarApariencia(campo, estado){	
	if(estado){
		campo.classList.remove("border-danger");
		campo.classList.add("border-success");
		campo.parentNode.nextElementSibling.style.visibility = 'hidden';	
	}
	else{
		campo.classList.remove("border-success");
		campo.classList.add("border-danger");
	
		campo.parentNode.nextElementSibling.style.visibility = 'visible';
	}
}