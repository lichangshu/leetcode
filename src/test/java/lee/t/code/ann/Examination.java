package lee.t.code.ann;

public @interface Examination {
    String value() default "";

    String[] description() default {};

    String url() default "";
}
