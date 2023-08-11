package hello.core.order;

import lombok.*;

@Getter
@Setter
@ToString
public class HelloLombok {

    @NonNull
    private int id;
    private String name;
    private int age;


    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        //롬복이 자동으로 만든 setter
        helloLombok.setName("moon");

//        //롬복이 자동으로 만든 getter
//        String name = helloLombok.getName();
//        System.out.println("name = " + name);
//        

        System.out.println("helloLombok = " + helloLombok);
    }

}
