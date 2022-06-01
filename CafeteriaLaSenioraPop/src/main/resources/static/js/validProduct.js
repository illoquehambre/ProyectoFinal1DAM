document.getElementById("nombre").addEventListener("blur",pasarNombreAMayusculas);
document.getElementById("precio").addEventListener("blur", comprobarPrecio);
document.getElementById("url").addEventListener("blur", comprobarUrl);

function revisarFormulario() {
	let resultado = false;


	resultado = pasarNombreAMayusculas() &&
		comprobarPrecio() &&
		comprobarUrl();


	formulario.enviar.className = resultado ? "btn btn-success mb-2" : "btn btn-danger mb-2";

	return resultado; 
}


function pasarNombreAMayusculas() {

	let campoNombre = document.getElementById("nombre");

	let resultado = campoNombre.value != "";
	if (resultado) {
		let array = new Array();
		array = campoNombre.value.split('');
		let letra = array.shift();
		letra = letra.toUpperCase();
		array.unshift(letra);
		campoNombre.value = array.join('');



	}

	//ver el comentario explicativo en el método cambiarApariencia
	cambiarApariencia(campoNombre, resultado);

	return resultado;
}

function comprobarPrecio() {
	let campoPrecio = document.getElementById("precio");

	let resultado = campoPrecio.value > 0 &&  /[0-9]+(\.[0-9][0-9]?)?/.test(campoPrecio.value);
	cambiarApariencia(campoPrecio, resultado);
	return resultado;
}

function comprobarUrl() {
	let campoUrl = document.getElementById("url");

	let resultado = /^(ftp|http|https):\/\/[^ "]+$/.test(campoUrl.value) || campoUrl.value == "";
	cambiarApariencia(campoUrl, resultado);
	return resultado;


}



function cambiarApariencia(campo, estado) {
	if (estado) {
		campo.classList.remove("border-danger");
		campo.classList.add("border-success");
		campo.parentNode.nextElementSibling.style.visibility = 'hidden';
	}
	else {
		campo.classList.remove("border-success");
		campo.classList.add("border-danger");

		campo.parentNode.nextElementSibling.style.visibility = 'visible';
	}
}