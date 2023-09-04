package ku.cs.kafe.service;

import ku.cs.kafe.entity.Category;
import ku.cs.kafe.entity.Menu;
import ku.cs.kafe.model.MenuRequest;
import ku.cs.kafe.repository.CategoryRepository;
import ku.cs.kafe.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Menu> getAllMenus(){
        return menuRepository.findAll();
    }

    public Menu getOneById(UUID id){
        return menuRepository.findById(id).get();
    }

    public void createMenu(MenuRequest menuRequest){
        Menu record = modelMapper.map(menuRequest, Menu.class);
        Category category = categoryRepository.findById(menuRequest.getCategoryId()).get();
        record.setCategory(category);
        menuRepository.save(record);
    }
}
