document.getElementById("nombre").addEventListener("blur", pasarNombreAMayusculas);

function revisarFormulario(){
	let resultado = false;

	resultado = pasarNombreAMayusculas();
	

	formulario.enviar.className = resultado?"btn btn-success mb-2":"btn btn-danger mb-2";

	return resultado; 
}


function pasarNombreAMayusculas(){

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