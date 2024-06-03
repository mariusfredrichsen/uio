public class FeilSporvidde extends Exception {
    public FeilSporvidde(String id, int sporvidde) {
        super(String.format("Tog med id %s har ikke samme sporvidde, har sporvidde: %s", id, sporvidde));
    }
}
