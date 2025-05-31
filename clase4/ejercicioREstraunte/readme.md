bjetivo del Proyecto:

Implementar un sistema básico de gestión de restaurantes aplicando conceptos de POO como:

    Encapsulamiento (atributos privados)
    Constructores en cadena
    Variables y métodos de clase (static)
    Métodos de cálculo
    Interfaz gráfica básica

Características Implementadas:

    Clase TipoRestaurante:

    Atributos: código, nombre, factorPrecio
    Variables de clase para contar tipos y sumar factores
    Tres constructores en cadena
    Métodos get/set completos

    Clase Restaurante:

    Atributos: ruc, nombre, tipo, capacidad, calificación
    Variables de clase para contar restaurantes y acumular ingresos
    Constantes para precio base y bono
    Métodos de cálculo según tipo de restaurante
    Tres constructores en cadena

    Interfaz Gráfica Simple:

    Un botón "Procesar" que ejecuta toda la lógica
    Área de texto para mostrar resultados
    Botones adicionales para limpiar y salir

Tabla de Precios por Tipo:

   Tipo de Restaurante
	

Precio Base (S/.)

     Gourmet
	

80.00

   Familiar
	

45.00

   Fast Food
	

25.00

   Pizzería
	

35.00

   Cevicheria
	

60.00

   Genérico
	

30.00
   Funcionalidades del Sistema:

    ✅ Creación de objetos con tres constructores diferentes
    ✅ Cálculo de precios según tipo de restaurante
    ✅ Aplicación de bonos por calificación alta (≥4.5)
    ✅ Acumulación automática de estadísticas
    ✅ Listado detallado de cada restaurante
    ✅ Interfaz gráfica simple y funcional
