const checkboxes = document.querySelectorAll('[name="serviciosIds"]');
		const spaCheckbox = document.querySelector('[data-nombre="Spa"]');
		const extrasSpa = document.querySelectorAll('.extra-spa');
		const resumen = document.getElementById('resumenPrecio');

		function getHoras(cb) {
			const linea = cb.closest('.servicio-linea');
			const horasInput = linea.querySelector('[name^="horas_"]');
			return horasInput ? parseInt(horasInput.value) || 1 : 1;
		}

		function calcularServicios() {
			let totalServicios = 0;
			checkboxes.forEach(cb => {
				if (cb.checked) {
					const precio = parseFloat(cb.dataset.precio) || 0;
					const horas = getHoras(cb);
					totalServicios += precio * horas;
				}
			});
			if (totalServicios === 0) {
				resumen.classList.add('d-none');
				return;
			}
			resumen.classList.remove('d-none');
			resumen.innerHTML = `<strong>Total servicios: ${totalServicios.toFixed(2)} €</strong>`;
		}

		function toggleHoras(cb) {
			const linea = cb.closest('.servicio-linea');
			const horasWrap = linea.querySelector('.horas-input');
			const numberInput = linea.querySelector('[name^="horas_"]');
			if (cb.checked) {
				horasWrap.classList.remove('d-none');
			} else {
				horasWrap.classList.add('d-none');
				if (numberInput) numberInput.value = 1;
			}
			calcularServicios();
		}

		function mostrarExtrasSpa() {
			if (!spaCheckbox) return;
			extrasSpa.forEach(extra => {
				const checkbox = extra.querySelector('[name="serviciosIds"]');
				extra.classList.toggle('d-none', !spaCheckbox.checked);
				if (!spaCheckbox.checked) {
					checkbox.checked = false;
					toggleHoras(checkbox);
				}
			});
			calcularServicios();
		}

		checkboxes.forEach(cb => {
			cb.addEventListener('change', () => toggleHoras(cb));
			const linea = cb.closest('.servicio-linea');
			const horasInput = linea.querySelector('[name^="horas_"]');
			if (horasInput) horasInput.addEventListener('input', calcularServicios);
		});

		if (spaCheckbox) spaCheckbox.addEventListener('change', mostrarExtrasSpa);