Package examples.hello;

Import jade.core.agent;

/**

   Este ejemplo sólo ejecuta un "HOLA MUNDO" y termina

   @author Isaac B 

 */

Public class helloworldagent extends agent {

  protected void setup() {

            system.out.println(“Hola mundo!! mi nombre es:_ “+getlocalname());

            // hacer que el agente se muera

            dodelete();

  }

}
