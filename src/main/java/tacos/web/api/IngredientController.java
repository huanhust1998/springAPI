package tacos.web.api;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.server.EntityLinks;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.entity.Ingredient;
import tacos.entity.Taco;
import tacos.repository.IngredientRepository;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")//produces: trả về kết quả dạng Json
@CrossOrigin(origins = "*")//cho phép gọi API từ máy chủ khác localhost
public class IngredientController {
    private IngredientRepository ingredientRepo;
    @Autowired
    //EntityLinks entityLinks;
    public IngredientController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @GetMapping
    public Iterable<Ingredient> getAllIngredients() {
        return ingredientRepo.findAll();
    }

    /*@PathVarialble(“id”) cho biết id sẽ lấy từ đường dẫn
    và sau đó được sử dụng trong phương thức như một tham số
     */
    //tìm kiếm theo id
    @GetMapping("/{id}")
    public Ingredient ingredientById(@PathVariable("id") String id) {
        Optional<Ingredient> optIngredient = ingredientRepo.findById(id);
        if (optIngredient.isPresent()) {
            return optIngredient.get();
        }
        return null;
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id){
        try {
            ingredientRepo.deleteById(id);
        }catch(EmptyResultDataAccessException e){
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient postIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }
}
