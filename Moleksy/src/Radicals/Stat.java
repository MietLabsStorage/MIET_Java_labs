package Radicals;

public enum  Stat {
    Atom,
    Radical,
    Binding;

    public static Stat parseStat(String name){
        if(name.equals(Atom.name())){
            return Atom;
        }
        if(name.equals(Radical.name())){
            return Radical;
        }
        if(name.equals(Binding.name())){
            return Binding;
        }
        return null;
    }
}
