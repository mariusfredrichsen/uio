```mermaid
    sequenceDiagram

        actor Bruker
        participant App
        participant Api

        
        Bruker ->> App: Click on retrive_weather-button

         
        App ->> Api: fetchWeatherData()

        alt suksess

        Api -->> App: return weather data

```
