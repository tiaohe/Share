import java.util.Optional;

public class OptionalUser {
    public static void main(String[] args) {

        String url = "StreamShowTime";

        String stream = Optional.ofNullable(url).filter(u -> u.startsWith("Stream")).orElse(null);
        //orElse 保底默认值
        String s = Optional.of(url).orElse(null);

        //避免空指针的异常（NullPointException）
        Optional<String> optionalValue = Optional.ofNullable(url);
        if(optionalValue.isPresent()){
            System.out.println(optionalValue.get() + " 不为空 ");
        }else {
            System.out.println("该值为null");
        }
        //optional链式调用
        Optional<String> url1 = Optional.of(url);
        url1.map(String::toLowerCase)
                .ifPresent(System.out::println);
    }
}
