# TodoApp (Android)

Aplicação de lista de tarefas feita para aprender desenvolvimento Android moderno.

## Funcionalidades
- Adicionar, marcar e apagar tarefas
- Contador de tarefas concluídas
- Limpar tarefas concluídas
- Dados guardados no telemóvel (funciona sem internet)

## Tecnologias
- Kotlin
- Jetpack Compose
- Arquitectura MVVM
- Room (base de dados local)
- Coroutines e Flow
- Hilt (injecção de dependência)
- JUnit e kotlinx-coroutines-test

## Arquitectura
UI (Compose) → ViewModel → Repository → DAO → Room

## Testes
`TaskRepositoryTest` testa o repositório com um DAO falso (4 testes).

## Como correr
1. Abre o projecto no Android Studio.
2. Espera o Gradle sync.
3. Corre num emulador ou telemóvel.
