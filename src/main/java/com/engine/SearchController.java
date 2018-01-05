package com.engine;


import com.engine.jpa.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.SetUtils;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class SearchController {

    @Autowired
    DocumentService documentService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    public String showAll(ModelMap model) {
        model.addAttribute("documents", documentService.findAllDocuments());
        return "list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("document", new Document());
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid Document document, Model model,
                      BindingResult result) {
        if(result.hasErrors()){
            return "add";
        }

        documentService.addNew(document);
        return "redirect:list";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("document", new Document());
        return "search";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute Document document,Model model) {
        Set documents = null;

        if(!StringUtils.isEmpty(document.getName())){
            documents = SetUtils.toSet(documentService.findDocumentByName(document.getName()));
        }else if(!StringUtils.isEmpty(document.getValues())){
            documents = documentService.findDocumentsByTokenNames(document);
        }

        model.addAttribute("documents", documents);

        return "list";
    }

}