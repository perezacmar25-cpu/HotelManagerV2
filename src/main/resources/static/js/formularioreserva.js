
		const PRECIOS_HABITACION = {
			'Individual': 55.0,
			'Doble': 100.0,
			'Suite': 155.0
		};

		const PRECIOS_COMIDA = {
			'SOLO_DESAYUNO': 10.0,
			'MEDIA_PENSION': 25.0,
			'PENSION_COMPLETA': 45.0,
			'TODO_INCLUIDO': 70.0
		};

		const fechaInicio = document.querySelector('[name="fechaInicio"]');
		const fechaFin = document.querySelector('[name="fechaFin"]');
		const tipoSelect = document.querySelector('[name="tipoHabitacion"]');
		const personasInput = document.querySelector('[name="numeroPersonas"]');
		const planComidaSelect = document.querySelector('[name="planComidaId"]');
		const resumen = document.getElementById('resumenPrecio');

		function calcularPrecio() {
			const inicio = fechaInicio.value ? new Date(fechaInicio.value) : null;
			const fin = fechaFin.value ? new Date(fechaFin.value) : null;
			const tipo = tipoSelect.value;
			const personas = Number(personasInput.value) || 0;
			const planComida = planComidaSelect.selectedOptions[0]?.dataset.tipo;

			if (!inicio || !fin || fin <= inicio || !tipo || personas <= 0) {
				resumen.classList.add('d-none');
				return;
			}

			const noches = Math.round((fin - inicio) / (1000 * 60 * 60 * 24));
			const precioHabitacion = PRECIOS_HABITACION[tipo] || 0;
			const precioComida = PRECIOS_COMIDA[planComida] || 0;
			const totalHabitacion = noches * precioHabitacion;
			const totalComida = noches * precioComida * personas;
			const total = totalHabitacion + totalComida;

			resumen.classList.remove('d-none');
			resumen.innerHTML = `
                <strong>Resumen de precio estimado</strong><br>
                ${noches} noche${noches !== 1 ? 's' : ''} x ${precioHabitacion.toFixed(2)} € = <strong>${totalHabitacion.toFixed(2)} €</strong><br>
				Plan comida: ${noches} noche${noches !== 1 ? 's' : ''} x ${personas} persona${personas !== 1 ? 's' : ''} x ${precioComida.toFixed(2)} € = <strong>${totalComida.toFixed(2)} €</strong><br>
                <strong>Total estancia: ${total.toFixed(2)} €</strong>
            `;
		}

		fechaInicio.addEventListener('change', calcularPrecio);
		fechaFin.addEventListener('change', calcularPrecio);
		tipoSelect.addEventListener('change', calcularPrecio);
		personasInput.addEventListener('input', calcularPrecio);
		planComidaSelect.addEventListener('change', calcularPrecio);
