package com.groovy.dynamicclasscreator.main

import com.groovy.dynamicclasscreator.util.ObjectCreator

class Main {
    public static void main(String[] args){
        Main main = new Main()
        Map<String, Class> variables = new HashMap<>()
        variables.put("id", Long.class)
        variables.put("firstName", String.class)
        variables.put("lastName", String.class)
        variables.put("department", String.class)
        variables.put("salary", Double.class)

        def employeePojo = main.createPojo(variables, "Employee")

        employeePojo.setId(1L)
        employeePojo.setFirstName("Suresh Kumar")
        employeePojo.setLastName("Mutukula")
        employeePojo.setDepartment("IT")
        employeePojo.setSalary(100000.00)

        println("************::: Dynamically Employee Object :::****************")
        println("Employee Id :: " + employeePojo.getId())
        println("Employee first name :: " + employeePojo.getFirstName())
        println("Employee last name :: " + employeePojo.getLastName())
        println("Employee department :: " + employeePojo.getDepartment())
        println("Employee Salary :: " + employeePojo.getSalary())
        println("***************************************************************")

    }
    public def createPojo(Map<String, Class> variables, String className){
        def classBuilder
        def pojo
        try{
            classBuilder = new ObjectCreator(new GroovyClassLoader())
            classBuilder.setClassName(className)
            variables?.each { k,v ->
                classBuilder.addField(k,v)
            }
            Class myClass = classBuilder?.getCreatedClass()
            pojo = myClass?.newInstance()
        }catch(Exception e){
            e.printStackTrace()
        }
        return pojo
    }
}
