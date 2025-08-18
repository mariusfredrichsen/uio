# Oppgave - ToDo App med Room database og git

1. Lag et repository på github og klon repositoriet.
    - Lag et Androis Studios prosjekt i den mappen som ble opprettet
    - Put endringene dine inn i staging area    (git add)
    - Legg filene til det lokale repositoriet   (git commit -m)
    - Last opp filene til github                (git push)

2. Lag en branch som heter "feat/ui" og bytt til den branchen

3. Lag en Composable komponent som heter "ToDoScreen"
    - Skal bestå av en LazyColumn som holder på en liste med gjøremål
    - Hvert gjøremål skal bli vist i et Card-composable med navn og beskrivelse
    - På bunnen av skjermen skal vi la brukeren kunne fylle inn i input felt for navn og beskrivelse for gjøremålet. I tillegg skal det være en knapp som legger til et gjøremål basert på hva som er i input feltene.

4. Legg til følegende dependencies i prosjektet:
```kotlin
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
```

5. Lag en ViewModel som heter "ToDoScreenViewModel"
    - Den skal holde på en StateFlow som er av typen ToDoScreenUIState ==> StateFlow<ToDoScreenUIState\>
    - ToDoScreenUIState data klassen skal ha en variabel "tasks" som holder på en liste med Task-objekter
    - ```kotlin
        data class ToDoScreenUiState(
            val tasks: List<Task> = listOf()
        )

        @Entity
        data class Task(
            @PrimaryKey(autoGenerate = true) val id: Int = 0,
            val name: String,
            val desc: String,
        )
        ```
    - ViewModelen skal støtte følgende funksjoner (implementer når det passer):
        - oppdatere UIState med alle tasks fra databasen
        - fjerne en eksisterende Task fra databasen
        - legge til en ny task i databasen

6. Merge branchen din "feat/ui" med main og lag en ny branch fra main som heter "feat/database" og bytt til denne branchen

7. Utvid data laget ved å lage "ToDoRepository.kt", "Task.kt" (fra ovenfra), "ToDoDao.kt", "ToDoDatabase.kt"
    - ToDoDatabase-en skal holde på Task entiteter og inneholde en ToDoDao
    - ToDoDao skal støtte:
        - Insert av en Task entitet
        - Slette en Task entitet
        - Hente alle Task entiteter
    - ToDoRepository skal støtte funksjonene i ToDoViewModel-en ved bruk av ToDoDao

8. Koble alt dette opp ved å opprette instanser av dem og legg dem inn som argumenter der hvor parameterne spør om det
    - Kan være noe kluss med Context