package com.android.domain.useCase.base

class BaseUseCaseExceptionImplementation :
    RuntimeException("Needs to provide implementation with defaultParams")

class BaseUseCaseExceptionImplementationWithBoolean :
    RuntimeException("Needs to provide implementation with defaultParams and value: Boolean")

class BaseUseCaseExceptionImplementationWithString :
    RuntimeException("Needs to provide implementation with defaultParams and value: String")