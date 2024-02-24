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
  // Chamando a função de teste
  realizarTestes()
}

fun realizarTestes() {
  // Criar usuários
  val usuario1 = Usuario.criarUsuario("João", "joao@test.com.br")
  val usuario2 = Usuario.criarUsuario("Maria", "maria@test.com.br")

  // Criar conteúdos educacionais com duração padrão
  val conteudo1 = ConteudoEducacional("Introdução à Programação")
  val conteudo2 = ConteudoEducacional("Algoritmos e Estruturas de Dados")
    
  // Criar formação e adicionar conteúdos educacionais pré-existentes
  val formacao1 = Formacao.criarFormacao(
    "Desenvolvimento de Software",
    mutableListOf(conteudo1, conteudo2),
    Nivel.INTERMEDIARIO
  )
    
  // Criar segunda formação com conteúdo novo e de duração personalizada
  val formacao2 = Formacao.criarFormacao(
    "Desenvolvimento em Kotlin",
    mutableListOf(ConteudoEducacional("Introdução ao Kotlin", 52)),
    Nivel.BASICO
  )
    
  // Testar dados dos usuários
  testarDadosUsuario(usuario1)
  testarDadosUsuario(usuario2)

  // Alterar o nível de um usuário
  usuario1.alterarNivel(Nivel.INTERMEDIARIO)
    
  // Testar dados do usuário1 após alteração de nível
  testarDadosUsuario(usuario1)

  // Testar alteração de nome do conteúdo educacional
  testeAlterarNome(conteudo1, "Programação em Python")

  // Matricular usuários na primeira formação
  formacao1.matricular(usuario1)
  formacao1.matricular(usuario2)

  // Testar alteração de nome da formação
  testeAlterarNome(formacao1, "Desenvolvimento Web")

  // Listar os usuários matriculados na primeira formação
  println("Usuários matriculados na formação 'Desenvolvimento de Software':")
  formacao1.listarInscritos()
  println()
    
  // Testar dados da segunda formação e dos conteúdos educacionais
  testarDadosFormacao(formacao1)

  // Testar dados da segunda formação e dos conteúdos educacionais
  testarDadosFormacao(formacao2)

  // Imprimir a lista de matriculados na segunda formação (deve estar vazia)
  println("Usuários matriculados na formação 'Desenvolvimento em Kotlin':")
  formacao2.listarInscritos()
}


fun testarDadosUsuario(usuario: Usuario) {
  println("Dados do Usuário:")
  println("Matrícula: ${usuario.id}")
  println("Nome: ${usuario.nome}")
  println("Email: ${usuario.email}")
  println("Nível: ${usuario.nivel}")
  println()
}

fun testarDadosFormacao(formacao: Formacao) {
  println("Dados da Formação:")
  println("Nome: ${formacao.nome}")
  println("Nível: ${formacao.nivel}")
  println("Conteúdos Educacionais:")
  formacao.conteudos.forEachIndexed { index, conteudo ->
    println("${index + 1}. Nome: ${conteudo.nome}, Duração: ${conteudo.duracao}")
  }
  println()
}

fun testeAlterarNome(objeto: Any, novoNome: String) {
  try {
    when (objeto) {
      is ConteudoEducacional -> {
        objeto.alterarNome(novoNome)
      }
      is Formacao -> {
        objeto.alterarNome(novoNome)
      }
      is Usuario -> {
        objeto.alterarNome(novoNome)
      }
      else -> throw IllegalArgumentException("Objeto não suportado para alteração de nome.")
    }
  } catch (e: Exception) {
    println("Erro ao tentar alterar o nome: ${e.message}")
  }
  println()
}
