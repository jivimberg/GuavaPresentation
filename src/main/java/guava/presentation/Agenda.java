package guava.presentation;

import guava.presentation.sections.*;
import guava.presentation.sections.caches.Caches;
import guava.presentation.sections.functionalidioms.FunctionalIdioms;
import guava.presentation.sections.objectmethods.ObjectMethods;

/**
 * This is a Guava Presentation. Yes a presentation, fuck Prezi.
 * It's objective is to introduce some features of the Guava Library we could use on our code.
 * I'll not cover all features in the Guava Library, instead I'll just highlight the ones I consider most useful.
 */
@SuppressWarnings("UnusedDeclaration")
public class Agenda {

    UsingAndAvoidingNull
            introducingOptional;

    ObjectMethods
            equalsComparisonEtc;

    ImmutableCollections
            whyUseItHowToCreateThem;

    NewCollectionTypes
            multiMapAndClassToInstanceMap;

    CollectionsStaticConstructors
            coolConstructors;

    Caches
            allAboutCaches;

    FunctionalIdioms
            collectionsFunctionalStyle;

    //TODO Concurrency -> Futures?
}
