public class Parkeringsplass <E> {
    private E kjøretøy;

    public void parker(E kjøretøy) {
        this.kjøretøy = kjøretøy;
    }

    public E kjørUt() {
        return this.kjøretøy;
    }
}
