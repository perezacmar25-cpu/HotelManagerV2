
		const PRECIOS_HABITACION = {
			'Individual': 55.0,
			'Doble': 100.0,
			'Suite': 155.0
		};

		const fechaInicio = document.querySelector('[name="fechaInicio"]');
		const fechaFin = document.querySelector('[name="fechaFin"]');
		const tipoSelect = document.querySelector('[name="tipoHabitacion"]');
		const resumen = document.getElementById('resumenPrecio');

		function calcularPrecio() {
			const inicio = fechaInicio.value ? new Date(fechaInicio.value) : null;
			const fin = fechaFin.value ? new Date(fechaFin.value) : null;
			const tipo = tipoSelect.value;

			if (!inicio || !fin || fin <= inicio || !tipo) {
				resumen.classList.add('d-none');
				return;
			}

			const noches = Math.round((fin - inicio) / (1000 * 60 * 60 * 24));
			const precioHabitacion = PRECIOS_HABITACION[tipo] || 0;
			const totalHabitacion = noches * precioHabitacion;

			resumen.classList.remove('d-none');
			resumen.innerHTML = `
                <strong>Resumen de precio estimado</strong><br>
                ${noches} noche${noches !== 1 ? 's' : ''} x ${precioHabitacion.toFixed(2)} € = <strong>${totalHabitacion.toFixed(2)} €</strong><br>
                <strong>Total estancia: ${totalHabitacion.toFixed(2)} €</strong>
            `;
		}

		fechaInicio.addEventListener('change', calcularPrecio);
		fechaFin.addEventListener('change', calcularPrecio);
		tipoSelect.addEventListener('change', calcularPrecio);
