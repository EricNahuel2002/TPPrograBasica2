# TPPrograBasica2
Integrantes: Aquino Eric, Debole Santiago, Pantano Esmeralda y Quelca Anahí.

Objetivo: Desarrollar un sistema de gestión para un restaurante, optimizando la administración de recursos humanos y mejorando la eficiencia en la atención al cliente a través de los registros de empleados, clientes y reservas.

Estructura: Este proyecto consta de 26 clases, distribuidas de la siguiente manera:
•   4 clases de herencia que ayudan a calcular el sueldo según el tipo de empleado.
•   1 clase interfaz.
•   5 clases de objetos.
•   1 clase intermediaria para la relación entre reservas y clientes.
•   1 clase de comparador.
• 13 clase exceptions
•   1 clase de pruebas (test) donde se verifica el funcionamiento de los métodos.

Para crear un sistema de gestión de restaurante, es fundamental establecer los siguientes métodos (45 en total):
Métodos para gestionar empleados y clientes
•	agregarEmpleado(): para agregar un empleado al restaurante.
•	buscarUnEmpleado(): para buscar un empleado específico.
•	despedirUnEmpleado(): para despedir a un empleado.
•	agregarCliente(): para agregar un cliente al restaurante.
•	buscarUnCliente(): para buscar un cliente específico.
•	cargarMeseroACargoDelEncargado(): para asignar meseros a un encargado.
•	cargarHorasTrabajadasDeUnEmpleado(): para registrar las horas trabajadas de un empleado.
•	asignarEmpleadoAEncargado(): para asignar un empleado a un encargado.
•	agregarReservaCliente(): añade una instancia de ReservaCliente a la lista de reservasClientes.

Métodos relacionados al sueldo de los empleados
•	cargarValorHoraDeUnEmpleado(): para cargar el valor por hora de un empleado.
•	cargarSueldoEmpleado(): para cargar el sueldo de un empleado.
•	obtenerSueldoDeUnEmpleado(): para obtener el sueldo de un empleado.
•	pagarSueldoAUnEmpleado(): para pagarle el sueldo a un empleado.
•	calcularElSueldoEnBaseALaAntiguedadDeUnEmpleado(): calcula el sueldo basado en la antigüedad del empleado.
Métodos para obtener listas organizadas de empleados, clientes y reservas:
•	obtenerListaDeEmpleadosOrdenadosAscendenteSinQueSeRepitaElCodigo(): para obtener una lista de empleados ordenados de manera ascendente sin repetir códigos.
•	obtenerListaDeEmpleadosOrdenadosPorUnOrdenEnEspecifico(): para obtener una lista de empleados ordenados según un criterio específico.
•	obtenerListaDeEmpleadosOrdenadosDescendentePorSueldo(): para obtener una lista de empleados ordenados de manera descendente por sueldo.
•	obtenerListaDeMeseros(): para obtener una lista de meseros.
•	obtenerListaDeMeserosPorAntiguedadDescendente(): para obtener una lista de meseros ordenados por antigüedad de manera descendente.
•	obtenerCantidadDeClientes(): para obtener el número total de clientes.
•	obtenerElMeseroDelMes(): para obtener el mesero del mes según cantidad de pedidos tomados.
•	obtenerListaDeEncargadosOrdenadoDeMayorAMenorPorSueldo(): obtiene una lista de encargados ordenados por sueldo de mayor a menor.
•	obtenerListaDeMeserosOrdenadoDeMayorAMenorPorSueldo(): obtiene una lista de meseros ordenados por sueldo de mayor a menor.
•	obtenerListaDeCajerosOrdenadoDeMayorAMenorPorSueldo(): obtiene una lista de cajeros ordenados por sueldo de mayor a menor.
•	obtenerEncargados(): obtiene una lista de todos los encargados.
•	obtenerReservaDeReservasClientes(Reserva reserva): obtiene una reserva específica de la lista de reservasClientes.
Métodos para gestionar interacciones entre meseros, clientes y reservas
•	agregarReserva(): agrega una reserva al restaurante.
•	buscarPedidoCliente(): busca una instancia de ReservaCliente que relacione una reserva específica con un cliente específico en una lista.
•	realizarReservaCliente(): realiza una reserva para un cliente en el restaurante.
•	buscarReserva(): busca una reserva.
•	agregarReservaCliente(): añade una instancia de ReservaCliente a la lista de reservasClientes.
•	obtenerHistorialDeReservasDeUnCliente(): obtiene el historial de reservas de un cliente específico.
•	queUnMeseroTomeUnaReservaCliente(): asigna un mesero específico a una reserva de cliente en particular.
•	agregarPedido(): añade un pedido al sistema y aumenta la cantidad de pedidos del mesero correspondiente.
•	buscarPedido(): busca un pedido específico en base a una reserva cliente y un empleado.
•	buscarReservaCliente(): busca una instancia específica de ReservaCliente en base a una reserva y un cliente.
•	buscarUnaReservaCliente(): busca una instancia específica de ReservaCliente en base a un cliente y una reserva.
•	buscarMesa(): busca una mesa específica.
•	agregarMesa(): añade una nueva mesa al sistema.
•	queUnMeseroPuedaTomarMasDeUnPedido(): permite que un mesero tome más de un pedido en una reserva cliente específica.
•	realizarReserva(): realiza una reserva para un cliente en una mesa específica.
Métodos de validación
•	validacionMesaNoEncontrada(): valida si una mesa no ha sido encontrada.
•	validacionReservaClienteDuplicado(): valida si una reserva cliente está duplicada.
•	validacionReservaNoEncontrada(): valida si una reserva no ha sido encontrada.
•	validacionClienteNoEncontrado(): valida si un cliente no ha sido encontrado.

Verificación de métodos:
Una vez implementados, estos métodos deben ser verificados con pruebas unitarias utilizando @Test para asegurar su correcto funcionamiento.

•	queUnEncargadoSeaUnaInstanciaDeLaClaseEncargado
•	queUnEncargadoSeaUnaSubClaseDeEmpleado
•	queUnCajeroSeaUnaInstanciaDeLaClaseCajero
•	queUnCajeroSeaUnaSubClaseDeEmpleado
•	queUnMeseroSeaUnaInstanciaDeLaClaseMesero
•	queUnMeseroSeaUnaSubClaseDeEmpleado
•	queSePuedaAgregarUnEmpleadoAlRestaurante
•	queAlAgregarUnEmpleadoQueYaEstaAgregadoLanceUnaException
•	queSePuedaDespedirAUnEmpleado
•	queAlDespedirUnEmpleadoNoExistenteLanceUnaException
•	queSePuedaAgregarUnClienteAlRestaurante
•	queSePuedaAgregarUnaReservaAlRestaurante
•	queSePuedaAgregarUnaReservaClienteAlRestaurante
•	queSePuedaBuscarUnEmpleadoPorSuId
•	queAlBuscarUnEmpleadoQueNoExistenteLanceUnaException
•	queSePuedaBuscarUnClientePorSuNumero
•	queSePuedaCalcularElSueldoDeUnEncargado
•	queSePuedaCalcularElSueldoDeUnMesero
•	queSePuedaCalcularElSueldoDeUnCajero
•	queSePuedaCalcularElSueldoDeUnEncargadoSinEmpleadosACargo
•	queSePuedaCalcularElSueldoDeUnMeseroSinPedidosRealizados
•	queSePuedaCalcularElSueldoDeUnCajeroSinAniosDeAntiguedad
•	queSePuedaOrdenarPorCodigoATodosLosEmpleadosQueTrabajanEnElrestauranteSinQueSeRepitan
•	queSePuedaOrdenarLaListaDeEmpleadosPorUnOrdenEspecificoSinQUeSeRepitaAlgunCodigoDescendentemente
•	queSePuedaOrdenarLosSueldosDeTodosLosEmpleadosQueTrabajanEnUnRestaurante
•	queSePuedaOrdenarDescendentementeLosSueldosDeLosEncargadosSinCalcularLaCantidadDeMesersosQueTieneASuCargoQueTrabajanEnUnRestaurante
•	queSePuedaOrdenarDescendenteLosSueldosDeLosMeserosQueTrabajanEnUnRestaurante
•	queSePuedaOrdenarDescendenteLosSueldosDeLosCajerosQueTrabajanEnUnRestaurante
•	queSePuedaObtenerLaCantidadDeMEserosQueTrabajanEnELRestaurante
•	queSePuedaObtenerListaDeLosCodigoDeLosMeserosOrdenadosPorAntiguedadDescendeteQueTrabajaEnElRestaurante
•	queSePuedaObtenerListaDeClienteSinQueSeRepitaElCodigo
•	queSePuedaObtenerElMeseroDelMes
•	queSePuedaObtenerLaCantidadDeEmpleadosQueTrabajaEnUnRestaurante
•	queSePuedaCrearUnaReservaCliente
•	queUnClientePuedaHacerUnaReserva
•	dadoQueUnClientePuedeHacerUnaReservaSiNoSeLoEncuentraQueSeLanceLaExceptionAdecuada
•	dadoQueUnClientePuedeHacerUnaReservaSiNoSeEncuentraLaReservaQueSeLanceLaExceptionAdecuada
•	dadoQueUnClientePuedeHacerUnaReservaQueNoPuedaHaberLaMismaReservaClienteDosVeces
•	queSePuedaCrearUnaMesa
•	queSePuedaAgregarUnaMesaAlRestaurente
•	queSePuedaBuscarUnaMesa
•	queSePuedaObtenerUnaReservaEntreLasReservasClientesDelRestaurante
•	queSePuedaAsignarUnaMesaAUnaReserva
•	dadoQueSePuedeAsignarUnaMesaAUnaReservaSiNoSeEncuentraLaMesaQueLanceException
•	dadoQueSePuedeAsignarUnaMesaAUnaReservaSiEsaReservaYaTieneAsignadaUnaMesaQueLanceException
•	queSePuedaBuscarUnaReservaCliente
•	queSePuedaCrearUnPedido
•	queUnMeseroTomeUnPedido
•	dadoQueUnMeseroTomaUnPedidoSiNoSeLoEncuentraQueSeLanceUnaExceptionAdecuada
•	dadoQueUnMeseroTomaUnPedidoSiNoSeEncuentraElPedidoQueLanceException
•	dadoQueUnMeseroTomaUnPedidoSiNoSeEncuentraElPedidoQueLanceException
•	dadoQueUnMeseroTomaLaReservaDeUnClienteQueNoSeDeLaMismaCombinacionDeReservaClienteMeseroDosVeces
•	queSePuedaAsignarEmpleadosAUnEncargado
•	dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiNoSeEncuentraElEncargadoQueSeLanceException
•	dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiUnEmpleadoYaFueAsignadoAEseEncargadoQueLanceException
•	queLanceUnaExcepcionSiQuieroObtenerElSueldoDeUnEmpleadoQueNoExiste
•	queAlBuscarUnClienteNoExistenteLanceUnaException
Esta estructura nos permite administrar eficaz y eficientemente un restaurante, abarcando la gestión de personal, la atención al cliente y la organización de reservas de manera.
Exclusiones del Proyecto:
1. Interacción con platos y tipos de chef: Este sistema no está diseñado para la gestión diferentes platos ofrecidos por el restaurante ni para gestionar los distintos tipos de chef. 
2. Funcionalidades específicas de cocina: No se incluyen métodos relacionados a la gestión de menús, recetas o ingredientes de la cocina.
3. Gestión de proveedores e inventario: No se abarca la gestión de proveedores ni la administración de compras de insumos para el restaurante (control de stock). Se limita a la gestión interna del personal, clientes y reservas.
4. Gestión financiera y sistemas externos: El sistema no aborda la contabilidad completa del restaurante (Ganancias, pérdidas, etc) ni la utilización de sistemas de pago externas (métodos de pago).
