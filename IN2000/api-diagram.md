```mermaid
    flowchart LR
        Start((Start))

        PartiesScreen
        PartyScreen

        End((End))

        Start --> PartiesScreen

        PartiesScreen --> PartyScreen

        PartyScreen --> PartiesScreen

        PartiesScreen --> End
        PartyScreen --> End



```