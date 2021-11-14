package club.vnco.crates.utils.command.context;

public interface RootContext extends Context {
	
    @Override
    RootContext getRoot();

    @Override
    boolean isRoot();
}
