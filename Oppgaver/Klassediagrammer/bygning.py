class Bygning:
    def __init__(self, navn):
        self._navn = navn
        self._rom = []

    def legg_til_rom(self, rom):
        self._rom.append(rom)
    
    def finn_storste_rom(self):
        storste_rom = self._rom[0]
        for rom in self._rom:
            if storste_rom.hent_antall_plasser() < rom.hent_antall_plasser():
                storste_rom = rom
        
        return storste_rom

