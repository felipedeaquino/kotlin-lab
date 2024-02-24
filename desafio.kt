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

data class ConteudoEducacional(var nome: String, val duracao: Int = 60) {
  fun alterarNome(novoNome: String) {
    nome = novoNome
    println("Nome da disciplina alterada de ${this.nome} para $novoNome com sucesso!")
  }
}

data class Formacao(
  var nome: String,
  var conteudos: MutableList<ConteudoEducacional>,
  val nivel: Nivel,
  val id: Int,
) {
  val inscritos = mutableListOf<Usuario>()

  companion object {
    private var contadorIds = 1

    fun criarFormacao(nome: String, conteudos: MutableList<ConteudoEducacional>, nivel: Nivel): Formacao {
      if (nome.isBlank() || conteudos.isEmpty()) {
        throw IllegalArgumentException("Nome e conteúdos não podem estar vazios.")
      }
      val id = contadorIds++
      return Formacao(nome, conteudos, nivel, id)
    }
  }

  fun matricular(usuario: Usuario) {
    inscritos.add(usuario)
  }

  fun listarInscritos() {
    if (inscritos.isEmpty()) {
      println("Não há usuários matriculados nesta formação.")
    } else {
      println("Alunos da formação $nome:")
      inscritos.forEach { println("${it.id} - ${it.nome}") }
    }
  }

  fun alterarNome(novoNome: String) {
    // Verifica se há alunos matriculados
    if (inscritos.isEmpty()) {
      // Se não houver alunos matriculados, altera o nome da formação
      println("Nome da Formação alterado de \"$nome\" para \"$novoNome\".")
      nome = novoNome
    } else {
      // Se houver alunos matriculados, não permite a alteração do nome
      throw IllegalStateException("Não é possível alterar o nome da formação pois há alunos matriculados.")
    }
  }
}

fun main() {
    TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}
