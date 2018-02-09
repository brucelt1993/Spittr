package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.Spitter;
import spittr.data.SpitterRepository;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.File;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }
    /*@RequestMapping(value="/register", method=GET)
    public String showRegistrationForm() {
        return "registerForm";
    }*/
    /**
     * 使用spring表单标签后的RequestMapping
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        //设置model的spitter的key,与表单commandName对应，否则不能正常渲染
        model.addAttribute(new Spitter());
        return "registerForm";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegistration(@RequestPart(value = "profilePicture",required = false) MultipartFile fileBytes,
            RedirectAttributes model, @Valid Spitter spitter, Errors errors) throws Exception{
        if(errors.hasErrors()){
            return "registerForm";
        }
        spitterRepository.save(spitter);
        fileBytes.transferTo(new File("\\upload\\"+fileBytes.getOriginalFilename()+".gif"));
        System.out.println("----->"+fileBytes.getOriginalFilename());
        model.addAttribute("username",spitter.getUsername());
        model.addFlashAttribute(spitter);
        return "redirect:/spitter/{username}";
    }
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Model model){
         if(!model.containsAttribute("spitter")) {
             Spitter spitter = spitterRepository.findByUsername(username);
             model.addAttribute(spitter);
         }
          return "profile";
    }
}
