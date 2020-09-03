package by.vit.myblog;

import by.vit.myblog.entity.Post;
import by.vit.myblog.entity.User;
import by.vit.myblog.exception.ForbiddenException;
import by.vit.myblog.service.PostService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostServiceTests {

    @Autowired
    private PostService postService;

    @Test
    public void getPaginated_InRange_ShouldReturnNotEmpty() {
        assertFalse(postService.getPaginated(PageRequest.of(0, 10)).isEmpty());
    }

    @Test
    public void getPaginated_OutOfRange_ShouldReturnEmpty() {
        assertTrue(postService.getPaginated(PageRequest.of(100, 100)).isEmpty());
    }

    @Test
    public void getById_ExistingId_ShouldReturnNotNull() {
        assertNotNull(postService.getById(1L));
    }

    @Test
    public void getById_NotExistingId_ShouldReturnNull() {
        assertNull(postService.getById(0L));
    }

    @Test
    public void save_Create_Valid_ShouldDoesNotThrow() {
        val user = new User();
        user.setId(8L);

        val post = new Post();
        post.setTitle("Тестируем создание нового поста в сервисе");
        post.setContent("Возможно, всё пойдёт наперекосяк...");
        post.setUser(user);

        assertDoesNotThrow(() -> postService.save(post));
    }

    @Test
    public void save_Update_Valid_ShouldDoesNotThrow() throws ParseException {
        val format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        val user = new User();
        user.setId(8L);

        val post = new Post();
        post.setId(2L);
        post.setTitle("Тестируем изменение поста в сервисе");
        post.setContent("И на этот раз есть вероятность провала");
        post.setCreation(format.parse("1999-05-03 12:30:00"));
        post.setUser(user);

        assertDoesNotThrow(() -> postService.save(post));
    }

    @Test
    public void save_NullTitle_ShouldThrowsException() {
        val user = new User();
        user.setId(8L);

        val post = new Post();
        post.setContent("А вот это точно не сработает");
        post.setUser(user);

        assertThrows(Exception.class, () -> postService.save(post));
    }

    @Test
    public void save_NullContent_ShouldThrowsException() {
        val user = new User();
        user.setId(8L);

        val post = new Post();
        post.setTitle("Eщё один неудачный пример");
        post.setUser(user);

        assertThrows(Exception.class, () -> postService.save(post));
    }

    @Test
    public void save_NullCreation_ShouldThrowsException() {
        val user = new User();
        user.setId(8L);

        val post = new Post();
        post.setTitle("Без даты тоже не получится");
        post.setContent("Будет ошибка, уверен!");
        post.setCreation(null);
        post.setUser(user);

        assertThrows(Exception.class, () -> postService.save(post));
    }

    @Test
    public void save_NullUser_ShouldThrowsException() {
        val post = new Post();
        post.setTitle("Пост без автора?");
        post.setContent("Автор должен отвечать за свои слова!");

        assertThrows(Exception.class, () -> postService.save(post));
    }

    @Test
    public void save_NullUserId_ShouldThrowsException() {
        val post = new Post();
        post.setTitle("Ай-яй-яй! Всё ещё не отвечает");
        post.setContent("Представьтесь, пожалуйста.");
        post.setUser(new User());

        assertThrows(Exception.class, () -> postService.save(post));
    }

    @Test
    public void save_NotExistingUserId_ShouldThrowsException() {
        val user = new User();
        user.setId(0L);

        val post = new Post();
        post.setTitle("Пытаетесь не своим именем назваться?");
        post.setContent("И такое, товарищ, будет предотвращено!");
        post.setUser(user);

        assertThrows(Exception.class, () -> postService.save(post));
    }

    @Test
    public void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> postService.deleteById(3L));
    }

    @Test
    public void deleteById_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> postService.deleteById(0L));
    }

    @Test
    public void create_ShouldDoesNotThrow() {
        val post = new Post();
        post.setTitle("Проверяем способность творить посты");
        post.setContent("А там и до сотворения мира недалеко");

        assertDoesNotThrow(() -> postService.create("Foma_1999", post));
    }

    @Test
    public void update_Valid_ShouldDoesNotThrow() {
        val post = new Post();
        post.setId(4L);
        post.setTitle("Этим человеком был Альберт Эйнштейн");
        post.setContent("Я не знаю каким оружием будут сражаться в Третьей мировой войне, но в Четвертой будут"
                + " сражаться палками и камнями.");

        assertDoesNotThrow(() -> postService.update("Foma_1999", post));
    }

    @Test
    public void update_NotAuthor_ShouldThrowsForbiddenException() {
        val post = new Post();
        post.setId(5L);
        post.setTitle("На границе тучи ходят хмуро");
        post.setContent("Край суровый тишиной объят");

        assertThrows(ForbiddenException.class, () -> postService.update("Foma_1999", post));
    }

    @Test
    public void delete_Valid_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> postService.delete("Foma_1999", 6L));
    }

    @Test
    public void delete_NotAuthor_ShouldThrowsForbiddenException() {
        assertThrows(ForbiddenException.class, () -> postService.delete("Foma_1999", 7L));
    }

    @Test
    public void delete_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> postService.delete("Foma_1999", 0L));
    }

}
