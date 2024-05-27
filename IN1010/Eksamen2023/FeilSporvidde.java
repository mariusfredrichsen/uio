public class FeilSporvidde extends Exception {
    public Feilsporvidde(String id, int sporvidde) {
        super(String.format("Tog med id %s har ikke samme sporvidde, har sporvidde: %s", id, sporvidde));
    }
}
