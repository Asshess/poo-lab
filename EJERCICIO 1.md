# Sistema de Control de Sueldos y Transportes

Una empresa de transporte de mercancías y personas requiere una aplicación orientada a objetos para gestionar la nómina y el cálculo de los sueldos a pagar a sus conductores.

Cada conductor dentro del sistema se encuentra identificado por su cédula de identidad, nombre, apellido y edad. El sueldo total asignado a cada conductor se calcula a partir de un sueldo base fijo de $ 700, al cual se le suman $ 5 por cada hora conducida en cualquiera de los transportes realizados, adicionando ademas las bonificaciones específicas de cada tipo de transporte ejecutado:

- Para el transporte de personas, se acredita un monto extra de $ 1 por hora si se trata de un transporte colectivo **(definido como aquel donde el número de personas transportadas es superior a 9)** o de $ 0,5 por hora si no es colectivo.
- Para el transporte de mercancías, se otorga un extra de $ 2 por cada tonelada trasladada.
- Por su parte, el transporte de mercancías peligrosas aplica la misma bonificación que el transporte de mercancías convencional más una asignación fija extra de $ 50 por cada servicio ejecutado.

El sistema debe contemplar la interacción del usuario mediante un menú interactivo. Para la captura y procesamiento de datos en esta implementación se exige combinar obligatoriamente el uso de java.util.Scanner para la lectura de datos por consola (como la navegación del menú y la lectura de cadenas/valores numéricos básicos) y *javax.swing.JOptionPane* para la entrada/salida gráfica de datos específicos (como la captura mediante cuadros de diálogo *showInputDialog* y el despliegue de alertas o informes consolidados de sueldos mediante *showMessageDialog)*. Las funciones administrativas a implementar deben permitir añadir conductores, asignar servicios de transporte a un conductor determinado, listar los transportes de mercancías peligrosas registrados e imprimir el informe detallado de sueldos en pantalla y diálogo visual.