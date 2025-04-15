package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.domain.Actividad;
import org.iesvdm.proyecto.domain.Comentario;
import org.iesvdm.proyecto.domain.Inscripcion;
import org.iesvdm.proyecto.domain.Usuario;
import org.iesvdm.proyecto.exception.ActividadNotFoundException;
import org.iesvdm.proyecto.exception.UsuarioNotFoundException;
import org.iesvdm.proyecto.repository.ActividadRepository;
import org.iesvdm.proyecto.repository.InscripcionRepository;
import org.iesvdm.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

//    public Inscripcion createInscipcion(Long idUsuario, Long idActividad, Inscripcion inscripcion) {
//        Usuario usuario = usuarioRepository.findById(idUsuario)
//                .orElseThrow(() -> new UsuarioNotFoundException(idUsuario));
//        inscripcion.setUsuario(usuario);
//
//        Actividad actividad = actividadRepository.findById(idActividad)
//                .orElseThrow(() -> new ActividadNotFoundException(idActividad));
//        inscripcion.setActividad(actividad);
//
//        return inscripcionRepository.save(inscripcion);
//    }

}