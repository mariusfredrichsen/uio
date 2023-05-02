class Tidspunkt implements Comparable<Tidspunkt> {
    int[] tid;
    Tidspunkt(int aar, int mnd, int dag, int time, int min, int sek) {
        int[] tmpTid = {aar, mnd, dag, time, min, sek};
        tid = tmpTid;
    }

    public int compareTo(Tidspunkt tidspunkt) {
        for (int i = 0; i < 6; i++) {
            if (tid[i] > tidspunkt.tid[i]) {
                return 1;
            } else if (tid[i] < tidspunkt.tid[i]) {
                return -1;
            }
        }
        return 0;
    }
}