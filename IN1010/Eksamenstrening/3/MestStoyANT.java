public class MestStoyANT {
    final int ANT;
    Maaling[] maalinger;

    MestStoyANT(int antall) {
        ANT = antall;
        maalinger = new Maaling[ANT];
    }

    public void settInnANT(Maaling m) {
        for (int i = 0; i < maalinger.length; i++) {
            if (maalinger[i] == null) {
                maalinger[i] = m;
                return;
            }
        }

        double lavestStoyNivaa = maalinger[0].stoyNivaa;
        int indexBeholder = 0;
        for (int i = 0; i < maalinger.length; i++) {
            if (maalinger[i].stoyNivaa < lavestStoyNivaa) {
                indexBeholder = i;
            }
        }

        if (maalinger[indexBeholder].stoyNivaa < m.stoyNivaa) {
            maalinger[indexBeholder] = m;
        }
    }

    public Maaling[] hentMaaling() {
        return maalinger;
    }


}
