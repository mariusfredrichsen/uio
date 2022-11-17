def velg_gave(mulige_gaver, onskeliste):
    for gaver in mulige_gaver:
        if gaver in onskeliste:
            return gaver
    return mulige_gaver[0]