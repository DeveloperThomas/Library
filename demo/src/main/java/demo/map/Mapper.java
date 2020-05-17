package demo.map;

public interface Mapper<E, DTO> {
    DTO map(E e);
}
