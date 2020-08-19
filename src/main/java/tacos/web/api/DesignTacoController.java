package tacos.web.api;

//import org.springframework.hateoas.server.EntityLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.entity.Taco;
import tacos.repository.TacoRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private TacoRepository tacoRepo;
    @Autowired
    //EntityLinks entityLinks;
    public DesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }
    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        return tacoRepo.findAll();
    }
    @GetMapping("/{id}")
    public Taco tacoById(@PathVariable("id") Integer id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return optTaco.get();
        }return null;
    }
    @PostMapping(consumes = "application/json")
    //consumes=“application/json” cho biết phương thức
    // có thể xử lý dữ liệu dạng JSON
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
}
