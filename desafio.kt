// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

class Usuario private constructor(
  val id: Int,
  var nome: String,
  val email: String,
) {
    var nivel: Nivel = Nivel.BASICO
      private set

    companion object {
      private var contadorIds = 1

      fun criarUsuario(nome: String, email: String, nivel: Nivel = Nivel.BASICO): Usuario {
        if (nome.isBlank() || email.isBlank()) {
          throw IllegalArgumentException("Nome e email não podem estar vazios.")
        }
        val id = contadorIds++
        return Usuario(id, nome, email).apply { this.nivel = nivel }
      }
    }

    fun alterarNivel(novoNivel: Nivel) {
      nivel = novoNivel
      println("Novo nível do Usuário de matrícula ${this.id}, ${this.nome}: ${this.nivel}.")
    	println()
    }
    fun alterarNome(novoNome: String) {
      nome = novoNome
      println("Nome do Usuário de matrícula ${this.id} alterado para $novoNome.")
      println()
    }
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
    }
}

fun main() {
    TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}
