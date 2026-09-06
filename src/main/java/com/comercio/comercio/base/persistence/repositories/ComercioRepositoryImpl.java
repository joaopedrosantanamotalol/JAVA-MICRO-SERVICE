    package com.comercio.comercio.base.persistence.repositories;

    import java.util.List;
    import java.util.Optional;

    import com.comercio.comercio.base.domain.entites.ComercioEntity;
    import com.comercio.comercio.base.domain.repositories.ComercioDOMAINRepository;
    import com.comercio.comercio.base.persistence.entites.ComercioPersistence;
    import com.comercio.comercio.base.persistence.mapper.ComercioMapper;


        public class ComercioRepositoryImpl implements ComercioDOMAINRepository {
        
        private final ComercioJPARepository repository;
        private final ComercioMapper mapper;

        

        public ComercioRepositoryImpl(ComercioJPARepository repository, ComercioMapper mapper) {
            this.repository = repository;
            this.mapper = mapper;
        }
        
        @Override
        public ComercioEntity salvar(ComercioEntity comercio){
            ComercioPersistence persistence = mapper.toPersistence(comercio);

            ComercioPersistence salvo = repository.save(persistence);

            return mapper.toDomain(salvo);
        }

            @Override
            public ComercioEntity editar(Long id, ComercioEntity comercio){

                ComercioPersistence persistence = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("nao foi possivel achar"));

                persistence.setCnpj(comercio.getCnpj());
                persistence.setComercio(comercio.getComercio());
                persistence.setDescricao(comercio.getDescricao());
                persistence.setNome(comercio.getNome());
                persistence.setTamanho(comercio.getTamanho());

                ComercioPersistence salvo = repository.save(persistence);

                return mapper.toDomain(salvo);

            }

        @Override
        public Optional<ComercioEntity> acharPorId(Long id) {

            return repository.findById(id)
                    .map(mapper::toDomain);
        }

        @Override
        public List<ComercioEntity> listar(){
            return repository.findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
        }

        @Override
        public void deletar(Long id){

            repository.deleteById(id);

        }

        @Override
        public boolean existePorId(Long id) {
            return repository.existsById(id);
        }


    }