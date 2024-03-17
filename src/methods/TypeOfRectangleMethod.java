package methods;

public enum TypeOfRectangleMethod {
    LEFT("левые"),
    RIGHT("правые"),
    MEDIUM("средние");

    private final String type;
    TypeOfRectangleMethod(String type){
        this.type = type;
    }

}
