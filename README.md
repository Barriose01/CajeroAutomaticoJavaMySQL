# CajeroAutomaticoJavaMySQL

ATENCION: Es necesario crear la base de datos utilizando el script MySql que se encuentra junto a los archivos, y utilizar el
servidor Xampp server. Es necesario tambien contar con un programa que sea capaz de ejecutar archivos .java, asi como mantener
estos archivos dentro del mismo directorio.

Al iniciar el programa, nos aparecera lo siguiente:

![image](https://user-images.githubusercontent.com/107152796/182439970-c46aaa92-d136-4456-a690-a0b3e06141fc.png)

Para utilizar el cajero, es necesario contar con una cuenta, por lo que pasaremos a crearla eligiendo la primera opcion.
Al hacer esto, el programa nos pedira algunos datos para poder crear la cuenta. Si la cuenta no esta registrada, esta
se registrara en la base de datos:

![image](https://user-images.githubusercontent.com/107152796/182440296-eaa67511-f3a2-446a-80d3-aa1da851250d.png)

Si se intenta registrar una cuenta que ya existe, no podremos registrarla:

![image](https://user-images.githubusercontent.com/107152796/182440468-c96db4e1-8271-428a-818d-2d1bcb7aa65b.png)

En este caso, el rut con el dv ya se encuentran en la base de datos, lo que significa que ya hay una persona registrada
con estos datos

Para iniciar sesion, elegimos la segunda opcion en el menu principal. Nos pedira que ingresemos nuestro rut, dv y la clave.

Si la cuenta esta registrada y los datos son correctos, se nos mostrara el menu del cajero:

![image](https://user-images.githubusercontent.com/107152796/185246146-afe3187d-92fa-4cc0-814e-29dfede564c7.png)

Tendremos distintas opciones. Como acabamos de crear la cuenta, si revisamos nuestro saldo, nos aparecera que tenemos $0.0:

![image](https://user-images.githubusercontent.com/107152796/182441277-32a397c1-82df-43a3-aa58-6960f059f596.png)

Para obtener saldo, es necesario realizar un deposito. Para ello, elegimos la opcion del deposito.

Se nos pedira que ingresemos el monto a depositar. Luego de esto, se nos actualizara el saldo:

![image](https://user-images.githubusercontent.com/107152796/182443373-a7bb3eb4-34dd-481f-9502-c13858de6150.png)

Ya teniendo saldo, podemos realizar retiros. Eligiendo la opcion de realizar retiros, nos aparecera un nuevo menu:

![image](https://user-images.githubusercontent.com/107152796/182443587-cb0abbff-51fc-4fcd-8772-899d31e6af9a.png)

Dependiendo de la opcion que eligamos, realizaremos un retiro por esa cantidad de dinero. Se nos descontara saldo de la cuenta:

![image](https://user-images.githubusercontent.com/107152796/182443736-07580777-660b-458d-81f8-f98a26475ec4.png)

Para realizar una transferencia, elegimos la opcion que hace referencia a esta accion. Ya aqui, se nos pedira que ingresemos el rut de la persona
a la cual le queremos transferir. Anteriormente, registramos otra cuenta para poder realizar este ejercicio. Llenamos los datos y nos pedira
que ingresemos el monto a transferir:

![image](https://user-images.githubusercontent.com/107152796/185246444-2d3d0337-cf7e-4f07-b833-c0bea9251de1.png)

Ingresamos el monto y nos aparecera esto:

![image](https://user-images.githubusercontent.com/107152796/185246552-1bcb00dc-3445-40c7-a97f-b2d51e4f57da.png)


Para ver todos los movimientos que hemos realizado, elegimos la opcion de ver movimientos. Nos mostrara informacion de las transacciones que hemos
realizado, incluyendo su fecha:

![image](https://user-images.githubusercontent.com/107152796/185246623-d6b33692-df9a-46e7-ba96-823f8982e1e7.png)

Para verificar que se realizo la transferencia correctamente, iniciamos sesion con la cuenta que creamos anteriormente y vemos sus movimientos:

![image](https://user-images.githubusercontent.com/107152796/185246746-0b20ac38-806d-47e3-a1e1-193da046966d.png)

Como podemos ver, hemos recibido una transferencia de un usuario con el rut de 11111111-k, el cual es el rut de la cuenta que creamos para hacer este
ejemplo






