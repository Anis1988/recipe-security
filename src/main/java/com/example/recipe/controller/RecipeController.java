package com.example.recipe.controller;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.domain.Difficulty;
import com.example.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_GUEST')")
    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe",new RecipeCommand());
        model.addAttribute("diffs", Difficulty.values());
        return "recipe/recipeform";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("recipe/{id}/update")
    public String Updaterecipe(@PathVariable String id,Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute ("recipe") RecipeCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "recipe/recipeform";
        }
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/"+savedCommand.getId()+"/show";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("recipe/{id}/delete")
    public String delteById(@PathVariable String id){
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
