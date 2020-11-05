package lee.t.code.ann;

public @interface Examination {
    String[] value();

    String url() default "";
}
