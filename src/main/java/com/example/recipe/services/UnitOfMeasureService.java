package com.example.recipe.services;

import com.example.recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

    public interface UnitOfMeasureService {

        Set<UnitOfMeasureCommand> listAllUoms();

}
