package club.vnco.crates.utils.command.internal;

import java.util.Map;

import com.google.common.base.Preconditions;

import lombok.AllArgsConstructor;
import club.vnco.crates.utils.command.Key;
import club.vnco.crates.utils.command.Provider;
import club.vnco.crates.utils.command.binding.Binding;
import club.vnco.crates.utils.command.binding.BindingBuilder;
import club.vnco.crates.utils.command.defaults.instance.InstanceProvider;

@AllArgsConstructor
public class AbstractBindingBuilder<T> implements BindingBuilder<T> {

	public Map<Key<?>, Binding<?>> map;
	public InternalBinding<T> binding;

    @Override
    public void toProvider(Provider<T> provider) {
        Preconditions.checkState(binding.getProvider() == null, "Provider already bound.");
        
        binding.setProvider(provider);
        
        map.put(binding.getKey(), binding);
    }

    @Override
    public void toInstance(T instance) {
        Preconditions.checkState(binding.getProvider() == null, "Provider already bound.");
        
        binding.setProvider(new InstanceProvider<>(instance));
        
        map.put(binding.getKey(), binding);
    }
}
